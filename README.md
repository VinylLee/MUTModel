# MUTModel

This is the dataset (aka. programs under test) used in the paper "MUT Model: A Metric for Characterizing Metamorphic Relations Diversity".

The programs selected for the experiments were respectively a Sparse Matrix Multiplication algorithm (SMM), an Earth Distance algorithm (ED), a classical trigonometric sine algorithm (SIN), a Dijkstra Shortest Path Algorithm (DSPA), and the Dnapars (DNA), a phylogenetic program commonly used in bio-informatics, which is used to infer evolutionary relationships among taxa using aligned sequences of characters, typically DNA or amino acids" [1][2]. All the tables of MRs are placed in Appendix Appendix  due to their length.

- SMM accepts two matrices and outputs the product of them. **Table: Matrix Multiplication** shows 18 MRs of the SMM, where $A^s$ is an $n\times p$ matrix and $B^s$ is a $p\times m$ matrix. 

- ED takes four inputs, which are the latitude and longitude of the first point and the second point, then it outputs the distance between the two points. **Table: EARTH DISTANCE** shows a total of 25 MRs for the Earth distance algorithm.

- SIN takes a value $x$ in radians and returns the value of $\sin{\left(x\right)}$. **Table: Sin** contains 18 MRs related to the $sin$ function.

- DSPA is used to find the shortest path from a specified source node to a specified destination node in an undirected graph $G$. **Table: DSPA** lists 15 MRs for the DSPA, where $G$ refers to the graph, $s$ and $e$ are two nodes in $G$, $s$ referring to the starting point and $e$ referring to the ending point. $DSPA\left(G,s,e\right)$ represents the sequence of nodes on the path from node $s$ to node $e$ in graph $G$ calculated by DSPA.

- DNA takes in a $u \times v$ matrix, which presents the DNA sequences with $u$ taxa and $v$ nucleotide. It outputs the phylogenetic tree and its length based on the input DNA sequences [3][4]. **Table: DNA** shows the 5 MRs [2] used in this study, where the $X$ denotes the input matrix. The $T$ and $t$ denote the output tree of $X$ and its length. The dnapars is a program in PHYLIP, please refer to https://csbf.stanford.edu/phylip/ .

All MRs can be found in the "src" directory as ".java" files. For all the tables above, please refer to the Appendix in the paper.
