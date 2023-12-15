import os


def check_tree_same(file_path1: str, file_path2: str) -> bool:
    def preprocess(line: str):
        line = line.replace("|", "")
        line = line.strip()
        line = line.replace("+", "")
        line = line.strip()
        line = line.replace("-", "")
        line = line.strip()
        return line

    with open(file_path1, "r") as file1, open(file_path2, "r") as file2:
        start = False
        for line1, line2 in zip(file1, file2):
            line1 = line1.strip()
            line2 = line2.strip()
            if line1.count("0 trees in all found") == 1 or line2.count("0 trees in all found") == 1:
                return False  # NOTE - 没可能没有树，一定会有树
            if line1.startswith("requires a total of") and not line2.startswith("requires a total of"):
                return False
            if not line1.startswith("requires a total of") and line2.startswith("requires a total of"):
                return False
            if line1.startswith("requires a total of") and line2.startswith("requires a total of"):
                return True
            if line1.startswith("+") and line2.startswith("+"):
                start = True
            elif line1.startswith("requires") and line2.startswith("requires"):
                start = False
            if start:
                line1 = preprocess(line1)
                line2 = preprocess(line2)
                if line1 != line2:
                    # print(f"file1: {line1}")
                    # print(f"file2: {line2}")
                    return False


def check_length_same(file_path1: str, file_path2: str, ratio: float = 1) -> bool:
    length1 = length2 = -1
    # 读取第一个文件
    with open(file_path1, "r") as file1:
        lines_file1 = file1.readlines()
    # 读取第二个文件
    with open(file_path2, "r") as file2:
        lines_file2 = file2.readlines()

    line1 = line2 = 0
    while line1 < len(lines_file1) or line2 < len(lines_file2):
        while line1 < len(lines_file1) and lines_file1[line1].startswith("requires a total of") != True:
            line1 += 1
        if line1 < len(lines_file1):
            length1 = float(lines_file1[line1].removeprefix("requires a total of").strip())
            line1 += 1
        while line2 < len(lines_file2) and lines_file2[line2].startswith("requires a total of") != True:
            line2 += 1
        if line2 < len(lines_file2):
            length2 = float(lines_file2[line2].removeprefix("requires a total of").strip())
            line2 += 1
        try:
            if length1 != -1 and length2 != -1 and length1 / length2 != ratio:
                return False
            elif length1 == -1 and length2 != -1:
                return False
            elif length1 != -1 and length2 == -1:
                return False
        except Exception as e:
            return False
    return True


# os.chdir("/home/muffin/MUT_model/dnapars")
# for mutant in range(0, 11):
#     print(check_tree_same(f"./output/m{mutant}/mr1_mu{mutant}_tc1f", f"./output/m{mutant}/mr1_mu{mutant}_tc1o"))
#     print(check_length_same(f"./output/m{mutant}/mr1_mu{mutant}_tc1f", f"./output/m{mutant}/mr1_mu{mutant}_tc1o"))
