# wsl-snapshot
Scala batch scripts for snapshotting and restoring WSL (Windows Subsystem for Linux). Meant to be run with ammonite (https://github.com/lihaoyi/Ammonite/releases).

# Setup
* Install JDK (we recommend OpenJDK from [Adoptium](https://adoptium.net/)), v8u252 or above. I have tried with JDK17 and things work just fine!
* Install [7-Zip](https://www.7-zip.org/). Should be available in C:\\Program Files\\7-Zip.
* Exit all WSL terminals, and all programs launched from WSL terminal (e.g., VSCode, IntelliJ etc)
  * Use `wsl -l -v` in Powershell, and then `wsl --terminate <distro name>`
* Save the "code" of this repository using the "Code" button, and downloading the zip file. Extract the contents of the zip file to somewhere on Windows (e.g., Desktop, or Downloads etc).
* Inside the folder, download the latest ammonite executable (currently 2.13-3.0.0-M1) from [https://github.com/lihaoyi/Ammonite/releases] and save it as `amm2.13-3.0.0-M1.jar`
  * Scala 3.X versions should also work - I have not tried.
* In order to save the snapshots, set up a folder. I prefer creating a folder called `WSLBackups` at `C:\Users\<username>\Documents\WSLBackups`

# Taking snapshot
* Open powershell (Admin mode is not required)
* Navigate to the source folder: `wsl-snapshot-master` (assuming you have downloaded, saved and extracted the code of the repo).
* Run the program to take snapshot.
```
java -jar .\<ammonite jar> .\snapshot.sc <Distro Name> <path\\to\\save>
```
The distro name can be obtained from running:
```$xslt
wsl.exe -l
```
Currently the default values for these two parameters are:
```$xslt
distro = Debian
pathToSave = C:\\Users\\<UserName>\\Documents\\WSLBackups
``` 
Notes: 
- Depending on how much content you have in WSL, it can take quite some time, and the command does not have a progress indicator.
- If 7-Zip is available, the snapshot file would be compressed automatically using the 7z format. This format was chosen since 7z can use parallelization to compress the large file.

# Restoring snapshot
* Open powershell (Admin mode is not required)
* Navigate to the git folder: `wsl-snapshot`
* Run the program to take snapshot.
```$xslt
java -jar .\<ammonite jar> .\restore-snapshot.sc <Distro name> <path\\to\\restore> <default user> <path\\to\\snapshot\\7z\\file>  
```
The distro name can be obtained from running:
```$xslt
wsl.exe -l
```
Currently the default values for the first two parameters are:
```$xslt
distro = Debian
pathToRestore = C:\\Users\\<UserName>\\Documents\\WSLRoot
``` 
Set the default user to the same username that was before in WSL, otherwise the home folder might be different.
