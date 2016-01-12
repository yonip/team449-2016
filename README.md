# team449-2016

  This is the repository storing the code for the 2016 FRC game Stronghold. It will also be the first year team 449 will have a planned file structure for the season, and likely for the upcoming years. This file structure is meant to be built such that all possible modules can be stored in one main repo, and a robot can be created from these modules, ranked by dependence.

## The file system
  
  The files in the system will be ranked in this fashion:
  ```
  └── module                     
    ├── ModuleName.java          
    ├── ModuleMap.java                        
    ├── components                        
    │   ├── Component1.java         
    │   └── Component2.java   
    ├── commands
    │   ├── Command1.java   
    │   └── Command2.java   
    └── submodule1                             
    │   ├── Submodule1Name.java                 
    │   ├── SubmoduleMap.java           
    │   ├── commands                      
    │   └── components                    
    └── submodule2                          
            ├── Submodule2Name.java         
            ├── Submodule2Map.java   
            ├── commands   
            │   └── Submodule2SpecialCommand.java 
            └── components                
  ```
  where any module is the direct superclass of its submodule, and any non-terminal module (ie on that has at least one subclasses) is abstract. Any components or commands shared within a reasonable number of modules should be in the `commands` folder that is at their rank or higher. The closer the folder is to root, the higher its rank.
  The point of this file system is such that and given module only requires the modules directly above it, and any commands or maps linked to it or the modules above it. Since, under these constraints, any module is self contained, this will allow for significant reuse of code between years and will also make the code easier to maintain. 
  If you have any questions about this layout, post an issue or contact me in some other way if you have access to it (email, chat etc.)


## Any required build files not in this repo
 - soon™
