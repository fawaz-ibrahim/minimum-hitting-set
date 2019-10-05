# Minimum Hitting Set
Minimum Hitting Set generation algorithm in JAVA

# Hitting set and set cover

Formally, let H = (V, E) be a hypergraph with vertex set V and hyperedge set E, and a number of subsets Ci ⊆ V; i=1..n , Then a set S ⊆ V is called hitting set, if for all Ci we have Ci ∩ S = ∅. And we call this S a (Minimal Hitting Set) if it was as smallest as possible, thus have the smallest possible size |S| (number of nodes).

# Algorithm
## Un-weighted algorithm
```
Un-weighted MHS (U all nodes, S all subsets)

NHS <- Collection of sets that haven’t been hit yet (equals S)
Sol <- Collection of nodes chosen as a solution
while NHS not empty Then
  x <- choose node from {U/Sol} that hits maximum count of s ∈ NHS
  add x to Sol
  remove Sx (as defined earlier) from NHS
Next
Measure solution |Sol|
```

## Weighted algorithm
```
Weighted MHS (U all nodes, S all subsets)

NHS <- Collection of sets that haven’t been hit yet (equals S)
Sol <- Collection of nodes chosen as a solution
while NHS not empty Then
  x <- choose node from {U/Sol} that has minimum cost = weight(x) / |Sx|
  add x to Sol
  remove Sx (as defined earlier) from NHS
Next
Measure solution |Sol| with cost Σ 𝑤𝑒𝑖𝑔ℎ𝑡(𝑥)∶𝑥∈𝑆𝑜𝑙
```

# License
GNU General Public License v3.0 [GPL v3.0](https://github.com/fawaz-ibrahim/minimum-hitting-set/blob/master/LICENSE)
