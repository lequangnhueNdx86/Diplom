import numpy as np


def calc_fft(inputList):
    temp = np.fft.fft(inputList)
    print(temp)
    res = []
    for c in temp:
        res.append(str(round(c.real, 5)) + (" " if c.imag < 0 else " +") + str(round(c.imag, 5)) + "j")
    return res
