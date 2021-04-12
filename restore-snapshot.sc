import java.io.File
import sys.process._

@main
def main(distro: String = "Debian", pathToRestore: String = "C:\\Users\\UserName\\Documents\\WSLRoot", defaultUser: String = "samik", snapshotPath: String) =
{
	val realPathToRestore = if(pathToRestore.contains("UserName")) pathToRestore.replace("UserName", sys.env("USERNAME")) else pathToRestore

	println(s"Restoring snapshot: [$distro] from [$snapshotPath] to [$realPathToRestore]")
	// Reference: https://www.howtogeek.com/426562/how-to-export-and-import-your-linux-systems-on-windows-10/
	// First check if the file is gzipped
	var tarFileName = snapshotPath
	if(snapshotPath.endsWith(".gz"))
	{
		// We need to uncompress the file
		val sevenZExec = new File("C:\\Program Files\\7-Zip\\7z.exe")
		if(sevenZExec.exists())
		{
			val archiveCmd = s"$sevenZExec e .\\$snapshotPath"
			println(s"Uncompressing: $snapshotPath")
			archiveCmd.!
			// Set the tar filename
			tarFileName = snapshotPath.stripSuffix(".gz")
		}
		else println(s"Couldn't locate 7z at [$sevenZExec]!")
	}
	// First, unregister the distro
	println(s"Executing: wsl.exe --unregister $distro")
	s"Executing: wsl.exe --unregister $distro".!
	// Next, restore the distro
	println(s"Executing: wsl.exe --import $distro $realPathToRestore $tarFileName")
	s"wsl.exe --import $distro $realPathToRestore $snapshotPath".!
	// Next set the default user, otherwise default user is root
	println(s"Setting default user for distro: $defaultUser")
	println(s"Executing: ${distro.toLowercase}.exe config --default-user $defaultUser")
	s"${distro.toLowercase}.exe config --default-user $defaultUser".!
	println("Snapshot restoration complete.")
}
