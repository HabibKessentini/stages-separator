# Workflow Stages Separator


We want to write a function to separate a given workflow’s steps into multiple stages
 in such a way that all the steps in each individual stage can run at the same time.
  
  The function should return a list of lists, in which each list represents one stage. 
  Each step should run in the earliest possible stage.

input = [["clean", "build"],
 ["clean", "metadata"],
 ["metadata", "binary"], 
 ["build", "link"],
 ["build", "resources"] 
 ["link", "binary"]] 
 
 
output = [["clean"],
 ["build", "metadata"],
 ["resources", "link"],
 ["binary"]]
