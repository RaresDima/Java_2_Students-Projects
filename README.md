# Java_2_Students-Projects

The Project Allocation Problem 
An instance of the problem involves a set of students and projects. A student may be enrolled in at most one project. Each project has a capacity constraint (an upper bound regarding how many students can be enrolled). 
Each student has a preference list over the available projects that he/she finds acceptable. Each project's coordinating professor also has a preference list over the students. 
We consider the problem of allocating students to projects based on these preference lists and capacity constraints.

The implementation consists of a fully stable matching. Although other stable matching algorithms such as Gale-Shapely are more efficient, they can have small errors or favor one set over the other. This is resolved with a sompatibility function that is used to evaluate all student-teacher pairs and commits them to the final allocation in descending order of compatibility, using the capacities of the different projects.
