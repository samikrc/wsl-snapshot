import sys.process._
import java.time.Instant
import java.util.Date

@main
def main(distro: String = "Debian", pathToSave: String = "C:\\Users\\Samik\\Documents\\WSLBackups", comment: String = "") =
{
	println(s"Generating snapshot: $distro: $pathToSave")
	// Reference: https://www.howtogeek.com/426562/how-to-export-and-import-your-linux-systems-on-windows-10/
	s"wsl.exe --export $distro $pathToSave\\$distro-${(new Date).toInstant}.tar" !
}