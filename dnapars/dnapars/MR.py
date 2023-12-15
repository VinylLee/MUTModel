from abc import ABC, abstractmethod
import os
import random
import shutil
import check
import createTC


class MR(ABC):
    ratio: int = 1

    def __init__(self):
        self.__name__ = self.__class__.__name__

    @abstractmethod
    def generate_follow_testcase(self, original_testcase: str):
        pass

    @abstractmethod
    def check_output(self, original_output: str, follow_output: str) -> bool:
        pass


# X' is constructed by inserting a number of uninformative sites(all bases the same) into X  Then, T=T′ and t=t'
class MR1(MR):
    def __init__(self) -> None:
        super().__init__()

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            added_col = random.randint(5, 10)
            added_base = random.choice(["A", "U", "C", "G"])
            added_base = "".join(added_base for _ in range(added_col))
            for line in file:
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    col = int(line.split("4      ")[1])
                    file_out.write(f"4      {col+added_col}\n")
                else:
                    line = line.strip()
                    # 以下修改dna
                    file_out.write(line + added_base + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_length_same(original_output, follow_output) and check.check_tree_same(original_output, follow_output)


# X'  is constructed by changing every alphabet in every sequence of X according to the same transformation scheme. Then, T=T′ and t=t′.
class MR2(MR):
    def __init__(self) -> None:
        super().__init__()

    def alter(self, dna: str) -> str:
        dna = dna.replace("A", "1")
        dna = dna.replace("U", "2")
        dna = dna.replace("C", "3")
        dna = dna.replace("G", "4")
        dna = dna.replace("1", "U")
        dna = dna.replace("2", "C")
        dna = dna.replace("3", "G")
        dna = dna.replace("4", "A")
        return dna

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            for line in file:
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    file_out.write(line)
                else:
                    line = line.strip()
                    # 以下修改dna
                    name = line.split("        ")[0]
                    dna = line.split("        ")[1]
                    file_out.write(name + "        " + self.alter(dna) + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_length_same(original_output, follow_output) and check.check_tree_same(original_output, follow_output)


# X' is constructed by swapping two sites in X. Then, T=T′ and t=t′
class MR3(MR):
    def __init__(self) -> None:
        super().__init__()

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            row = -1
            random_col = -1
            for line in file:
                line = line.strip()
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    col = int(line.split("      ")[1])
                    random_col = random.randint(1, col - 2)
                    file_out.write(line + "\n")
                else:
                    # 以下修改dna
                    name1 = line.split("        ")[0]
                    dna1 = line.split("        ")[1]
                    dna_prefix = dna1[:random_col]
                    dna_afterfix = dna1[random_col + 2 :]
                    file_out.write(name1 + "        " + dna_prefix + dna1[random_col + 1] + dna1[random_col] + dna_afterfix + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_length_same(original_output, follow_output) and check.check_tree_same(original_output, follow_output)


# X' is constructed by removing some uninformative sites(all bases the same) from X  Then, T=T′ and t=t'
class MR4(MR):
    def __init__(self) -> None:
        super().__init__()

    def remove_uninformative_bases(self, dnas: list[str], cnt=-1):
        i = 0
        while i < len(dnas[0]):
            # 获取当前位置的碱基
            base = dnas[0][i]
            # 检查其他DNA序列在相同位置是否都有相同的碱基
            if cnt != 0 and all(dna[i] == base for dna in dnas):
                # 如果都相同，则删除该位置的碱基
                dnas = ["".join(dna[:i] + dna[i + 1 :]) for dna in dnas]
                cnt -= 1
            else:
                i += 1
        return dnas

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        sites_to_remove = random.randint(3, 5)
        names: list[str] = []
        dnas: list[str] = []
        with open(original_testcase, "r") as file:
            lineNo = 0
            for line in file:
                line = line.strip()
                lineNo += 1
                if lineNo > 1:
                    names.append(line.split("        ")[0])
                    dnas.append(line.split("        ")[1])
        self.remove_uninformative_bases(dnas, sites_to_remove)
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            for line in file:
                line = line.strip()
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    row = line.split("      ")[0]
                    file_out.write(row + "        " + str(len(dnas[0])) + "\n")
                else:
                    # 以下修改dna
                    file_out.write(names[lineNo - 2] + "        " + dnas[lineNo - 2] + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_length_same(original_output, follow_output) and check.check_tree_same(original_output, follow_output)


# X' is constructed by adding a hyper-variable site to X, then T'=T
class MR5(MR):
    ratio = 0

    def __init__(self) -> None:
        super().__init__()

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        hyper_sites = ["A", "U", "C", "G"]
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            for line in file:
                line = line.strip()
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    row = int(line.split("      ")[0])
                    if row != 4:
                        raise Exception("row not equals to 4")
                    col = int(line.split("      ")[1])
                    file_out.write(str(row) + "      " + str(col) + "\n")
                else:
                    # 以下修改dna
                    file_out.write(line + hyper_sites[lineNo - 2] + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_tree_same(original_output, follow_output)


# X' is constructed by concatting the duplication of X, then T'=T and t'=2t
class MR6(MR):
    ratio = 0.5

    def __init__(self) -> None:
        super().__init__()

    def generate_follow_testcase(self, original_testcase: str):
        follow_testcase = original_testcase.replace("o", "f")
        with open(original_testcase, "r") as file, open(follow_testcase, "w") as file_out:
            lineNo = 0
            for line in file:
                line = line.strip()
                lineNo += 1
                if lineNo == 1:
                    # 以下修改数量
                    row = int(line.split("      ")[0])
                    col = int(line.split("      ")[1]) * 2
                    file_out.write(str(row) + "      " + str(col) + "\n")
                else:
                    # 以下修改dna
                    dna = line.split("      ")[1]
                    file_out.write(line + dna + "\n")

    def check_output(self, original_output: str, follow_output: str):
        return check.check_tree_same(original_output, follow_output) and check.check_length_same(original_output, follow_output, 0.5)


class CMR(MR):
    def __init__(self, *mrs: MR) -> None:
        self.mrs = mrs
        self.num_of_mrs = len(self.mrs)
        super().__init__()
        self.__name__ += "_".join(mr.__name__.removeprefix("MR") for mr in self.mrs)

    def generate_follow_testcase(self, original_testcase: str):
        temp_testcase = original_testcase.replace("o", "t")
        follow_testcase = original_testcase.replace("o", "f")
        shutil.copyfile(original_testcase, temp_testcase)
        for mr in self.mrs:
            mr.generate_follow_testcase(original_testcase)
            shutil.copyfile(follow_testcase, original_testcase)
        os.remove(original_testcase)
        os.rename(temp_testcase, original_testcase)

    def check_output(self, original_output: str, follow_output: str):
        res = None
        for mr in self.mrs:
            self.ratio *= mr.ratio
        if self.ratio != 0:
            return check.check_tree_same(original_output, follow_output) and check.check_length_same(original_output, follow_output, self.ratio)
        else:
            return check.check_tree_same(original_output, follow_output)
