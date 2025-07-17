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

java -jar underdocx-vortrag-0.1-jar-with-dependencies.jar debug ./%inputFile% ./%outputFile% ./presentationData.json
start "" ./%outputFile%
pause