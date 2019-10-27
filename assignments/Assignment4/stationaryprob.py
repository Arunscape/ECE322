import numpy as np
import string

a = np.array(
    [
        # a  b  c  d  e
        [-1, 0, 0, 0, 0.5],
        [1, -1, 1, 0, 0],
        [0, 0.6, -1, 0, 0.5],
        [0, 0.4, 0, -1, 0],
        #[0, 0, 0, 1, -1],
        [1, 1, 1, 1, 1],
    ]
)

#b = np.array([0, 0, 0, 0, 0, 1])
b = np.array([0, 0, 0, 0, 1])

x = np.linalg.solve(a, b)

alphabet = string.ascii_uppercase

d = dict()
for i in range(len(x)):
    d[alphabet[i]] = x[i]

for k,v in sorted(d.items(), key=lambda x: x[1], reverse=True):
        print(f"{k}: {v}")
