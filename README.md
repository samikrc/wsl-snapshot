# wsl-batch
Scala batch scripts for snapshotting and restoring WSL (Windows Subsystem for Linux). Meant to be run with ammonite (https://github.com/lihaoyi/Ammonite/releases).

# Setup
* Install JDK (we recommend OpenJDK from [AdoptOpenJDK](https://adoptopenjdk.net/)), v8u252 or above.
* Install [7-Zip](https://www.7-zip.org/). Should be available in C:\\Program Files\\7-Zip.
* Open your WSL terminal (the following steps can also be done on Windows side, e.g., in git bash).
  * Clone the repo: `git clone git@github.com:samikrc/wsl-snapshot.git`
  * Inside the `wsl-snapshot` folder, download the ammonite executable from [https://github.com/lihaoyi/Ammonite/releases]. For example: `wget https://github.com/lihaoyi/Ammonite/releases/download/2.0.4/2.12-2.0.4 -O amm2.12-2.0.4.jar`
  * Exit all WSL terminals, and possibly all programs launched from WSL terminal (e.g., VSCode, IntelliJ etc)
* In order to save the snapshots, set up a folder. I prefer creating a folder called `WSLBackups` at `C:\Users\<username>\Documents\WSLBackups`

# Taking snapshot
* Open powershell (Admin mode is not required)
* Navigate to the git folder: `wsl-snapshot`
  * If your git folders are on wsl, and you have not snapshot or restored an earlier snapshot before, it is likely that to navigate to the git folder `wsl-snapshot`, you need to know where Windows 10 stores your Linux distro files. Follow this link for this purpose: [https://www.howtogeek.com/261383/how-to-access-your-ubuntu-bash-files-in-windows-and-your-windows-system-drive-in-bash/]. For example, my Debian files were located at `C:\Users\<username>\AppData\Local\Packages\TheDebianProject.DebianGNULinux_76v4gfsz19hv4`
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
- If 7-Zip is available, the snapshot file would be gzipped automatically.

# Restoring snapshot
* Open powershell (Admin mode is not required)
* Navigate to the git folder: `wsl-snapshot`
* Run the program to take snapshot.
```$xslt
java -jar .\<ammonite jar> .\restore-snapshot.sc <Distro name> <path\\to\\restore> <path\\to\\snapshot\\gzip>  
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
