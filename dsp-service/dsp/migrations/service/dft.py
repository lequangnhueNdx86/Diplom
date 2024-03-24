from pathlib import Path

import numpy as np
import pandas as pd
# matplotlib inline
import matplotlib.pyplot as plt
import matplotlib as mpl
from scipy import fftpack
from scipy import signal


class DFT:

    def __init__(self, a1, om1, a2, om2):
        if om1 <= om2:
            self.a1 = a1
            self.om1 = om1
            self.a2 = a2
            self.om2 = om2
        else:
            self.a1 = a2
            self.om1 = om2
            self.a2 = a1
            self.om2 = om1

    def low_pass_filter(self):
        for path in Path("/result/dft").glob("**/*"):
            if path.is_file():
                path.unlink()

        f_s = 1
        N = 256
        T = N / f_s
        t = np.linspace(0, T, N)

        alpha = 1 / (2 * (np.cos(self.om1) - np.cos(self.om2)))
        beta = - 2 * alpha * np.cos(self.om2)
        b = [alpha, beta, alpha]

        x_pass = self.a1 * np.cos(self.om1 * t)
        x_stop = self.a2 * np.cos(self.om2 * t)
        x = x_pass + x_stop
        y = signal.lfilter(b, 1, x)
        fig, axs = plt.subplots(3, 1, sharey=True, sharex=True)
        fig.set_size_inches((8, 4))
        ax = axs[0]
        ax.plot(t, x_pass, color="k")
        ax.plot(t, x_stop)
        ax.set_ylabel("Components", fontsize=11)
        ax.set_ylim([- self.a1 - self.a2 - 1, self.a1 + self.a2 + 1])
        ax.legend(loc=0, fontsize=11)
        ax.grid(True)

        ax = axs[1]
        ax.plot(t, x, color="r")
        ax.set_ylabel("Filter input", fontsize=11)
        ax.legend(loc=0, fontsize=11)
        ax.grid(True)

        ax = axs[2]
        ax.plot(t, y, color="g")
        ax.set_ylabel("Filter output", fontsize=11)
        ax.set_xlabel("Time (sec)", fontsize=11)
        ax.legend(loc=0, fontsize=11);
        ax.grid(True)
        plt.savefig("result/dft/low_pass_filter_input_signal_and_output_signal.png")
        ########################################################
        f = fftpack.fftfreq(N, 1 / f_s)
        mask = np.where(f > 0)
        X = fftpack.fft(x)
        Y = fftpack.fft(y)
        X_mask = 20 * np.log10(abs(X[mask]) / N)
        Y_mask = 20 * np.log10(abs(Y[mask]) / N)
        fig, ax = plt.subplots(figsize=(8, 4))
        plt.plot(np.linspace(0, np.pi, len(X_mask)), X_mask, label="filter input")
        plt.plot(np.linspace(0, np.pi, len(Y_mask)), Y_mask, label="filter output")
        ax.set_ylabel("20log(X(f))", fontsize=11)
        ax.legend(loc=0)
        fig.tight_layout()
        plt.grid(True)
        plt.savefig("result/dft/frequency_response_of_the_low_pass_filter.png")
        return {"imageList": ["result/dft/low_pass_filter_input_signal_and_output_signal.png",
                              "result/dft/frequency_response_of_the_low_pass_filter.png"],
                "alpha": f'{round(alpha, 5)}',
                "beta": f'{round(beta, 5)}'
                }

    def high_pass_filter(self):
        for path in Path("/result/dft").glob("**/*"):
            if path.is_file():
                path.unlink()
        f_s = 1
        N = 256
        T = N / f_s
        t = np.linspace(0, T, N)

        alpha = 1 / (2 * (np.cos(self.om2) - np.cos(self.om1)))
        beta = - 2 * alpha * np.cos(self.om1)
        b = [alpha, beta, alpha]

        x_stop = self.a1 * np.cos(self.om1 * t)
        x_pass = self.a2 * np.cos(self.om2 * t)
        x = x_pass + x_stop
        y = signal.lfilter(b, 1, x)
        fig, axs = plt.subplots(3, 1, sharey=True, sharex=True)
        fig.set_size_inches((8, 4))
        ax = axs[0]
        ax.plot(t, x_pass, color="k")
        ax.plot(t, x_stop)
        ax.set_ylabel("Components", fontsize=11)
        ax.set_ylim([- self.a1 - self.a2 - 1, self.a1 + self.a2 + 1])
        ax.legend(loc=0, fontsize=11)
        ax.grid(True)

        ax = axs[1]
        ax.plot(t, x, color="r")
        ax.set_ylabel("Filter input", fontsize=11)
        ax.legend(loc=0, fontsize=11)
        ax.grid(True)

        ax = axs[2]
        ax.plot(t, y, color="g")
        ax.set_ylabel("Filter output", fontsize=11)
        ax.set_xlabel("Time (sec)", fontsize=11)
        ax.legend(loc=0, fontsize=11);

        ax.grid(True)
        plt.savefig("result/dft/high_pass_filter_input_signal_and_output_signal.png")

        f = fftpack.fftfreq(N, 1 / f_s)
        mask = np.where(f > 0)
        X = fftpack.fft(x)
        Y = fftpack.fft(y)
        X_mask = 20 * np.log10(abs(X[mask]) / N)
        Y_mask = 20 * np.log10(abs(Y[mask]) / N)
        fig, ax = plt.subplots(figsize=(8, 4))
        plt.plot(np.linspace(0, np.pi, len(X_mask)), X_mask, label="filter input")
        plt.plot(np.linspace(0, np.pi, len(Y_mask)), Y_mask, label="filter output")
        ax.set_ylabel("20log(X(f))", fontsize=11)
        ax.legend(loc=0)
        fig.tight_layout()
        plt.grid(True)
        plt.savefig("result/dft/frequency_response_of_the_high_pass_filter.png")
        return {"imageList": ["result/dft/high_pass_filter_input_signal_and_output_signal.png",
                              "result/dft/frequency_response_of_the_high_pass_filter.png"],
                "alpha": f'{round(alpha, 5)}',
                "beta": f'{round(beta, 5)}'
                }
