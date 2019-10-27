import numpy as np
import string

a = np.array(
    [
        # a  b  c  d  e  f  g
        [-1, 0, 0, 1, 0, 0, 0.4],
        [1, -1, 0, 0, 0, 0, 0],
        [0, 0.4, -1, 0, 0, 0, 0],
        [0, 0.6, 0.6, -1, 1, 0.2, 0.4],
        [0, 0, 0.3, 0, 0, -1, 0],
        [0, 0, 0, 0, 0, 0.8, -0.8],
        [1, 1, 1, 1, 1, 1, 1],
    ]
)

b = np.array([0, 0, 0, 0, 0, 0, 1])

x = np.linalg.solve(a, b)

alphabet = string.ascii_uppercase

d = dict()
for i in range(len(x)):
    d[alphabet[i]] = x[i]

for k,v in sorted(d.items(), key=lambda x: x[1], reverse=True):
        print(f"{k}: {v}")
