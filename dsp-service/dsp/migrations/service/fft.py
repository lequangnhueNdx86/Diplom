import numpy as np

from dsp.migrations.algorithm.algorithm import *


def init_matrix():
    return [[complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)],
            [complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0),
             complex(0, 0)]
            ]


def init_res():
    return [['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ['', '', '', '', '', '', '', ''],
            ]


W08 = 1
W18 = complex(np.sqrt(2) / 2, -np.sqrt(2) / 2)
W28 = complex(0, -1)
W38 = complex(-np.sqrt(2) / 2, -np.sqrt(2) / 2)


class FFT:
    def __init__(self, inputList):
        self.inputList = inputList  # x0 -> x7

    def calc_freq_fft(self):
        res = calc_fft(self.inputList)
        return [res[0], res[4], res[2], res[6], res[1], res[5], res[3], res[7]]

    def calc_time_fft(self):
        return calc_fft(self.inputList)

    def calc_time_matrix(self):
        T = init_matrix()
        # cột 1
        T[0][0] = self.inputList[0]
        T[1][0] = self.inputList[4]
        T[2][0] = self.inputList[2]
        T[3][0] = self.inputList[6]
        T[4][0] = self.inputList[1]
        T[5][0] = self.inputList[5]
        T[6][0] = self.inputList[3]
        T[7][0] = self.inputList[7]
        # cột 2
        for i in range(8):
            if i % 2 == 0:
                T[i][1] = T[i][0]
            else:
                T[i][1] = T[i][0] * W08
        # cột 3
        for i in range(8):
            if i % 2 == 0:
                T[i][2] = T[i][1] + T[i + 1][1]
            else:
                T[i][2] = T[i - 1][1] - T[i][1]
        # cột 4
        T[0][3] = T[0][2]
        T[1][3] = T[1][2]
        T[2][3] = T[2][2] * W08
        T[3][3] = T[3][2] * W28
        T[4][3] = T[4][2]
        T[5][3] = T[5][2]
        T[6][3] = T[6][2] * W08
        T[7][3] = T[7][2] * W28
        # cột 5
        T[0][4] = T[0][3] + T[2][3]
        T[1][4] = T[1][3] + T[3][3]
        T[2][4] = T[0][3] + T[2][3] * (-1)
        T[3][4] = T[1][3] + T[3][3] * (-1)
        T[4][4] = T[4][3] + T[6][3]
        T[5][4] = T[5][3] + T[7][3]
        T[6][4] = T[4][3] + T[6][3] * (-1)
        T[7][4] = T[5][3] + T[7][3] * (-1)
        # cột 6
        T[0][5] = T[0][4]
        T[1][5] = T[1][4]
        T[2][5] = T[2][4]
        T[3][5] = T[3][4]
        T[4][5] = T[4][4] * W08
        T[5][5] = T[5][4] * W18
        T[6][5] = T[6][4] * W28
        T[7][5] = T[7][4] * W38
        # cột 7
        T[0][6] = T[0][5] + T[4][5]
        T[1][6] = T[1][5] + T[5][5]
        T[2][6] = T[2][5] + T[6][5]
        T[3][6] = T[3][5] + T[7][5]
        T[4][6] = T[4][5] * (-1) + T[0][5]
        T[5][6] = T[5][5] * (-1) + T[1][5]
        T[6][6] = T[6][5] * (-1) + T[2][5]
        T[7][6] = T[7][5] * (-1) + T[3][5]
        # cột 8
        for i in range(8):
            T[i][7] = T[i][6]

        res = init_res()
        for i in range(8):
            for j in range(8):
                if j == 0:
                    res[i][j] = str(round(T[i][j].real, 5))
                else:
                    res[i][j] = str(round(T[i][j].real, 5)) + ("" if T[i][j].imag < 0 else "+") + str(
                        round(T[i][j].imag, 5)) + "j"

        return res

    def calc_freq_matrix(self):
        F = init_matrix()
        # cot 1
        for i in range(8):
            F[i][0] = self.inputList[i]
        # cot 2
        F[0][1] = F[0][0] + F[4][0]
        F[1][1] = F[1][0] + F[5][0]
        F[2][1] = F[2][0] + F[6][0]
        F[3][1] = F[3][0] + F[7][0]
        F[4][1] = F[4][0] * (-1) + F[0][0]
        F[5][1] = F[5][0] * (-1) + F[1][0]
        F[6][1] = F[6][0] * (-1) + F[2][0]
        F[7][1] = F[7][0] * (-1) + F[3][0]
        # cot 3
        F[0][2] = F[0][1]
        F[1][2] = F[1][1]
        F[2][2] = F[2][1]
        F[3][2] = F[3][1]
        F[4][2] = F[4][1] * W08
        F[5][2] = F[5][1] * W18
        F[6][2] = F[6][1] * W28
        F[7][2] = F[7][1] * W38
        # cot 4
        F[0][3] = F[0][2] + F[2][2]
        F[1][3] = F[1][2] + F[3][2]
        F[2][3] = F[0][2] + F[2][2] * (-1)
        F[3][3] = F[1][2] + F[3][2] * (-1)
        F[4][3] = F[4][2] + F[6][2]
        F[5][3] = F[5][2] + F[7][2]
        F[6][3] = F[4][2] + F[6][2] * (-1)
        F[7][3] = F[5][2] + F[7][2] * (-1)
        # cot 5
        F[0][4] = F[0][3]
        F[1][4] = F[1][3]
        F[2][4] = F[2][3] * W08
        F[3][4] = F[3][3] * W28
        F[4][4] = F[4][3]
        F[5][4] = F[5][3]
        F[6][4] = F[6][3] * W08
        F[7][4] = F[7][3] * W28
        # cot 6
        F[0][5] = F[0][4] + F[1][4]
        F[1][5] = F[0][4] + (-1) * F[1][4]
        F[2][5] = F[2][4] + F[3][4]
        F[3][5] = F[2][4] + (-1) * F[3][4]
        F[4][5] = F[4][4] + F[5][4]
        F[5][5] = F[4][4] + (-1) * F[5][4]
        F[6][5] = F[6][4] + F[7][4]
        F[7][5] = F[6][4] + (-1) * F[7][4]
        # cot 7
        F[0][6] = F[0][5]
        F[1][6] = F[1][5] * W08
        F[2][6] = F[2][5]
        F[3][6] = F[3][5] * W08
        F[4][6] = F[4][5]
        F[5][6] = F[5][5] * W08
        F[6][6] = F[6][5]
        F[7][6] = F[7][5] * W08

        res = init_res()
        for i in range(8):
            for j in range(8):
                if j == 0:
                    res[i][j] = str(round(F[i][j].real, 5))
                else:
                    res[i][j] = str(round(F[i][j].real, 5)) + (" " if F[i][j].imag < 0 else " +") + str(
                        round(F[i][j].imag, 5)) + "j"

        return res
