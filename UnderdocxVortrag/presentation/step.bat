cd /D "%~dp0"
set inputFile=initialPresentation.odp
set outputFile=generated01.odp
if exist generated01.odp (
    set inputFile=generated01.odp
    set outputFile=generated02.odp
)
if exist generated02.odp (
    set inputFile=generated02.odp
    set outputFile=generated03.odp
)
if exist generated03.odp (
    set inputFile=generated03.odp
    set outputFile=generated04.odp
)
if exist generated04.odp (
    set inputFile=generated04.odp
    set outputFile=generated05.odp
)
if exist generated05.odp (
    set inputFile=generated05.odp
    set outputFile=generated06.odp
)
if exist generated06.odp (
    set inputFile=generated06.odp
    set outputFile=generated07.odp
)
if exist generated07.odp (
    set inputFile=generated07.odp
    set outputFile=generated08.odp
)

java -jar underdocx-vortrag-0.1-jar-with-dependencies.jar ./%inputFile% ./%outputFile% ./presentationData.json
start "" ./%outputFile%
pause