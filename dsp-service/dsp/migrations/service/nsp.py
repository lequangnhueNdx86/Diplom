from pathlib import Path

import numpy as np
import matplotlib.pyplot as plt
from scipy.fft import fft, fftfreq


class NSP:
    def __init__(self, frequency, noisy_freq, num_samples, sampling_rate):
        self.frequency = frequency
        self.noisy_freq = noisy_freq
        self.num_samples = num_samples
        self.sampling_rate = sampling_rate

    def calc(self):
        for path in Path("/result/nsp").glob("**/*"):
            if path.is_file():
                path.unlink()
        # write a wave generator function
        sine_wave = [np.sin(2 * np.pi * self.frequency * x1 / self.sampling_rate) for x1 in range(self.num_samples)]
        sine_noise = [np.sin(2 * np.pi * self.noisy_freq * x1 / self.sampling_rate) for x1 in range(self.num_samples)]
        # convert to array
        sin_wave = np.array(sine_wave)
        sine_noise = np.array(sine_noise)
        # generate noise signals
        combined_signal = sine_wave + sine_noise
        # check FFT transform
        data_fft = np.fft.fft(combined_signal)
        freq = (np.abs(data_fft[:len(data_fft)]))
        # Use FFT filter, that is, use numpy function.
        # First, we will create an array filter_freq; index variable to compare frequency conditions.
        # Then follow the following algorithm
        filtered_freq = [f if (self.frequency - self.noisy_freq < index < self.frequency + self.noisy_freq and f > 1) else 0 for index, f in enumerate(freq)]
        recovered_signal = np.fft.ifft(filtered_freq)
        plt.subplot(3, 1, 1)
        plt.title("Original sine wave")
        plt.subplots_adjust(hspace=.5)
        plt.plot(sine_wave[:500])
        plt.grid(True)
        plt.subplot(3, 1, 2)
        plt.title("Noisy wave")
        plt.plot(combined_signal[:4000])
        plt.grid(True)
        plt.subplot(3, 1, 3)
        plt.title("Sine wave after clean up")
        plt.plot(recovered_signal[:500])
        plt.grid(True)
        plt.savefig("result/nsp/sine_wave.png")
        plt.close()
        return ["result/nsp/sine_wave.png"]