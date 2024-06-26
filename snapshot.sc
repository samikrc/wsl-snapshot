import java.io.File

import sys.process._
import java.util.Date

/**
 Usage: java -jar .\ammX.X.X.jar .\snapshot.sc
*/
@main
def main(distro: String = "Debian", pathToSave: String = "C:\\Users\\UserName\\Documents\\WSLBackups") =
{
	val realPathToSave = if(pathToSave.contains("UserName")) pathToSave.replace("UserName", sys.env("USERNAME")) else pathToSave

	println(s"Generating snapshot for distro: $distro, at: $realPathToSave")
	// Reference: https://www.howtogeek.com/426562/how-to-export-and-import-your-linux-systems-on-windows-10/
	val targetFilename = s"$distro-${(new Date).toInstant.toString.replace(":","-").split('.')(0)}.tar"
	val command = s"wsl.exe --export $distro \"$realPathToSave\\$targetFilename\""
	println(s"Executing: $command")
	command.!
	// Make sure the snapshot tar file exists
	if(new File(s"$realPathToSave\\$targetFilename").exists)
	{
		println(s"Successfully generated: \"$realPathToSave\\$targetFilename\"")
		// Now gzip the archive
		val sevenZExec = new File("C:\\Program Files\\7-Zip\\7z.exe")
		if(sevenZExec.exists())
		{
			println(s"Creating gzip archive from ${targetFilename}...")
			// Use 7z format since that happens in a parallel manner. 
			val output = s"${realPathToSave}\\${targetFilename}.7z"
			val archiveCmd = s"$sevenZExec a -t7z \"$output\" \"$realPathToSave\\$targetFilename\""
			println(s"Executing: $archiveCmd")
			try
			{
				archiveCmd.!
				println(s"Snapshot complete! Output file: [$output]")
			}
			catch
			{
				case e: Exception => println(s"Could not create archive. Error: ${e.getMessage}")
			}			
		}
		else println(s"Error: Couldn't locate 7z at [$sevenZExec]!")
	}
	else
	{
		println(s"Error: snapshot file $targetFilename has not been generated!")
		System.exit(-1)
	}
}
