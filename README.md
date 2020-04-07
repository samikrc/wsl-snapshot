# wsl-batch
Scala batch scripts for snapshotting and restoring WSL (Windows Subsystem for Linux). Meant to be run with ammonite (https://github.com/lihaoyi/Ammonite/releases).

# Setup
* Need to have java installed in Windows
* Open your WSL terminal (the following steps can also be done on Windows side, e.g., in git bash).
  * Clone the repo: `git clone git@github.com:samikrc/wsl-snapshot.git`
  * Inside the `wsl-snapshot` folder, download the ammonite executable from [https://github.com/lihaoyi/Ammonite/releases]. For example: `wget https://github.com/lihaoyi/Ammonite/releases/download/2.0.4/2.12-2.0.4 -O amm2.12-2.0.4.jar`
  * Exit all WSL terminals, and possibly all programs launched from WSL terminal (e.g., VSCode, IntelliJ etc)
* In order to save the snapshots, set up a folder. I prefer creating a folder called `WSLBackups` at `C:\Users\<username>\Documents\WSLBackups`


# Taking snapshot
* Open powershell (Admin mode is not required)
* Navigate to the git folder: `wsl-snapshot`
* Run the program to take snapshot.
```
java -jar .\amm2.12-2.0.4.jar .\snapshot.sc
```
Note: Depending on how much content you have in WSL, it can take quite some time, and the command does not have an indicator.
