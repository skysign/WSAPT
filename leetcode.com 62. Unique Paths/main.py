import os
import shutil
import subprocess
import numpy as np
from pslolviz import *

class Pslolviz:
    def __init__(self, name, array):
        self.name = name
        self.array = array
        self.frame_count = 0

        self.rm_rf(name)

        prefix = name + '/' + 'png' + '/'
        self.mkdir_p(prefix)

        prefix = name + '/' + 'png_for_video' + '/'
        self.mkdir_p(prefix)

    def save2DArray2Img(self, idx_row, idx_col):
        # name, array, frame_count,
        self.frame_count += 1
        self.save2DArray2ImgInternal(self.name, self.array, 'png', self.frame_count, idx_row, idx_col)
        self.save2DArray2ImgInternal(self.name, self.array, 'png_for_video', self.frame_count, idx_row, idx_col)

    def save2DArray2ImgInternal(self, name, array, image_purpose, frame_count, idx_row, idx_col):
        cnt_row = len(array)
        cnt_col = len(array[0])
        max_digits = max(self.getDigits(cnt_row), self.getDigits(cnt_col))
        # digits = 1
        # %01d
        # digits = 2
        # %02d

        prefix = name + '/' + image_purpose + '/'
        self.mkdir_p(prefix)
        filename = ''

        if image_purpose == 'png':
            filename_format = prefix
            number_format = '%0' + str(max_digits) + 'd'
            filename_format += number_format + 'x' +number_format  # for both row count and column count
            filename_format += '_' + number_format + 'x' + number_format  # for both row index and column index
            # filename = './dp_' + str(cnt_row) + 'x' + str(cnt_col) + '_' + str(idx_row) + 'x' + str(idx_col)
            filename = filename_format % (cnt_row, cnt_col, idx_row, idx_col)
        elif image_purpose == 'png_for_video':
            filename_format = prefix
            number_format = '%0' + '9' + 'd'
            filename_format += number_format
            filename = filename_format % (frame_count)

        filename_dot = filename + '.dot'

        g = matrixviz(array, idx_row, idx_col)
        g.render(format='png', filename=filename_dot)

    def generate_video(self):
        # 'ffmpeg -r 1 -i dp_%d.dot.png output.mp4'
        # prefix = name + '/' + 'png_for_video' + '/'
        image_path = self.name + '/' + 'png_for_video' + '/%09d.dot.png'
        output_filename = self.name + '.mp4'

        # self.rm_rf(output_filename)

        try:
            os.remove(output_filename)
        except:  # 예외가 발생했을 때 실행됨
            print('except ' + output_filename)

        cmds = ['ffmpeg.exe', '-y', '-r', '1', '-i', image_path, output_filename]

        try:
            subprocess.check_call(cmds)
        except:  # 예외가 발생했을 때 실행됨
            print('except ' + 'ffmpeg.exe')


    def mkdir_p(self, path):
        """Creates a directory recursively."""
        if not os.path.exists(path):
            os.makedirs(path)

    def rm_rf(self, path):
        # check if directory exists
        if os.path.exists(path):
            # remove directory and all its contents
            shutil.rmtree(path)

    def getDigits(self, num):
        digits = 1

        # reminder가 10보다 작을 때 까지
        remainder = num

        while remainder > 10:
            remainder = num % 10
            digits += 1

        return digits

class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        dp[1][1] = 1

        for idx_row in range(1, m + 1):
            for idx_col in range(1, n + 1):
                dp[idx_row][idx_col] += (dp[idx_row - 1][idx_col] + dp[idx_row][idx_col - 1])

        return dp[m][n]


    def uniquePaths_dump_dp(self, m: int, n: int) -> int:
        # dp = [ [0 for _ in range(n +1)] for _ in range(m +1)]

        dp = np.zeros((m +1, n +1), dtype=int)
        pslolviz: Pslolviz = Pslolviz('dp', dp)

        dp[1][1] = 1

        for idx_row in range(1, m +1):
            for idx_col in range(1, n+1):
                dp[idx_row][idx_col] += (dp[idx_row -1][idx_col] + dp[idx_row][idx_col -1])
                pslolviz.save2DArray2Img(idx_row, idx_col)

        pslolviz.generate_video()

        return dp[m][n]

