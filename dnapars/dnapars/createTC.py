import random


def generate_dna(length: int) -> str:
    bases = ["A", "U", "C", "G"]
    return "".join(random.choice(bases) for _ in range(length))


def createTC(file_path: str):
    col = random.randint(10, 38)
    with open(file_path, "w") as file:
        lines_to_write = [
            f"4      {col+1}",
            f"Alpha        A{generate_dna(col)}",
            f"Beta        A{generate_dna(col)}",
            f"Gamma        A{generate_dna(col)}",
            f"Delta        A{generate_dna(col)}",
        ]

        for line in lines_to_write:
            file.write(line + "\n")
