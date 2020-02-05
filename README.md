# Diff Algorithms

This repo is a collection of several diff algorithms and Java implementation including native dynamic programming method, A* search, Myers Algorithm and RWS-diff algorithm for comparing tree structures.

## Dynamic Programming

Popular algorithm especially in interview for SES problem, O(n^2) time complexity.

## A* Search

Inspired by a blog: https://thume.ca/2017/06/17/tree-diffing/#example-implementation

This algorithm works better than the native dynamic solution which needn't walk through the whole graph. The time
complexity is O(n * e^2), e is the edit distance.

## Myers Diff Algorithm

Paper: An O(ND) Difference Algorithm and Its Variations by EUGENE W. MYERS
See: http://www.xmailserver.org/diff2.pdf

## RWS-diff Algorithm

Paper: RWS-Diff: Flexible and Efficient Change Detection in Hierarchical Data
See: https://db.in.tum.de/~finis/papers/RWS-Diff.pdf

This algorithm has a O(n*log(n)) time complexity
