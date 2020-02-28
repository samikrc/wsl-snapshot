import sys.process._
import java.time.Instant
import java.util.Date

@main
def main(distro: String = "Debian", pathToRestore: String = "C:\\Users\\Samik\\Documents\\WSLRoot", snapshotPath: String) =
{
	println(s"Restoring snapshot: $distro: $snapshotPath")
	// Reference: https://www.howtogeek.com/426562/how-to-export-and-import-your-linux-systems-on-windows-10/
	// First, restore the distro
	println(s"Executing: wsl.exe --import $distro $pathToRestore $snapshotPath")
	s"wsl.exe --import $distro $pathToRestore $snapshotPath" !
	// Next set the default user, otherwise default user is root
	val defaultUser = "samik"
	println(s"Executing: ${distro.toLowercase}.exe config --default-user $defaultUser")
	s"${distro.toLowercase}.exe config --default-user $defaultUser" !
}