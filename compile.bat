@echo off

type help.txt

cmd /C mvn -DskipTests -P compilarfront  %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED PINBALMONITOR_DEPLOY_DIR (
	  @echo on
	  echo --------- COPIANT EAR ---------

	  xcopy /Y pinbalmonitor-ear\target\pinbalmonitor.ear %PINBALMONITOR_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn PINBALMONITOR_DEPLOY_DIR 
	  echo    apuntant al directori de deploy del JBOSS  i automaticament 
	  echo    s'hi copiara l'ear generat.
	  echo  =================================================================
	) 

)