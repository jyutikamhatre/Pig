# Pig
Proof of concept - Hadoop - PIG


Project Description
Analyzing the log file of IBM Notes client software installation failure
Dataset Resource
Installation log file on my machine
Special operation
1. reading semi-structured file
2. filter, group,count, order
3. dump and store output on HDFS
4. use UDF in Pig to show uppercase
5. show output in descending order
Goal
Analyze the log file using Apache Pig to find out the kind and type of messages or errors and its total number of count.  So that the troubleshooter can take faster decision for next action on software installation failure
Java Class for UDF
UpperUDFForPig
UDF jar
UpperUDFForpig.jar
Input file
/input/IBMNotesInstall.log
Output file
/output/logbycount
/output/logbytext

log = load '/input/IBMNotesInstall.log'

logtypes = foreach log generate REGEX_EXTRACT($0,'(UpgradeUtils/StartAutoLog|UpgradeUtils/UpdateUpgradeTable|UpgradeUtils/SetPropertiesForSecureProperty|GetProperty Success|UpgradeUtils/GetPropertiesForSecureCustom|SetProperty Success|Function IsNotesPluginInstall|UpgradeUtils/uiInitialize|Function IsUpgradeBuild|UpgradeUtils/IsUninstall|UpgradeUtils/CountNotes6Products|UpgradeUtils/CountNotes6Products|UpgradeUtils/IsMSIMajorUpgrade|UpgradeUtils/SetInstallType|MSI ERROR|UpgradeUtils/SetPathsFromRegistry|UpgradeUtils/SetProgramFolderToProgramFolderVar|UpgradeUtils/IsInstallFromAdminImage|UpgradeUtils/CheckKitRequiredFiles|SUCCESS|Function INTL_IsMuiIns|UpgradeUtils/UI_SetProperties|UpgradeUtils/StreamlineCmdLine|UpgradeUtils/DumpUpgradeTable|UpgradeUtils/ES_SetProperties|SetTargetPath Success|GetTargetPath Success|UpgradeUtils/FileExists|UpgradeUtils/RemoveObsoleteFiles|UpgradeUtils/RemoveExistingFiles|UpgradeUtils/INTL_AdjustComponentsState|UpgradeUtils/CleanLockPermissionsTable|UpgradeUtils/SetREDIR_DATADIR|UpgradeUtils/SetREDIR_VDIR_SHARED|UpgradeUtils/FinalActions|UpgradeUtils/RegisterMAPI|WritePrivateProfileString Success|UpgradeUtils/CreateSignatureFiles|UpgradeUtils/UpdateRegForMultiUser|UpgradeUtils/WriteIniFile|UpgradeUtils/Check_VDIR_INI_OVERRIDE|UpgradeUtils/FileExists|UpgradeUtils/INTL_ClearOutIniForUpgrade|UpgradeUtils/INTL_UpdateMUIDAT|PAUL->|INTL_GetReleaseBuild|UpgradeUtils/DisableMUIPack)',1) as logtypetext;

filteredlogtypes = filter logtypes by logtypetext is not null;

groupedlogtypes = group filteredlogtypes by logtypetext;

frequencies = foreach groupedlogtypes generate group as logtypetext,COUNT(filteredlogtypes.logtypetext) as CNT;

resultbyCNT = order frequencies by CNT desc;

dump resultbyCNT;

store resultbyCNT into '/output/logbycount';

Output lines sample.....

Success!

Job Stats (time in seconds):
JobId   Maps    Reduces MaxMapTime      MinMapTime      AvgMapTime      MedianMapTime   MaxReduceTime   MinReduceTime   AvgReduceTime   MedianReducetime        Alias   Feature Outputs
job_1498887855726_0004  1       1       4       4       4       4       3       3       3       3       filteredlogtypes,frequencies,groupedlogtypes,log,logtypes       GROUP_BY,COMBINER
job_1498887855726_0005  1       1       4       4       4       4       4       4       4       4       resultbyCNT     SAMPLER
job_1498887855726_0006  1       1       3       3       3       3       4       4       4       4       resultbyCNT     ORDER_BY        /output/logbycount,

Input(s):
Successfully read 2048 records (245218 bytes) from: "/input/IBMNotesInstall.log"

Output(s):
Successfully stored 45 records (1429 bytes) in: "/output/logbycount"

Counters:
Total records written : 45
Total bytes written : 1429
Spillable Memory Manager spill count : 0
Total bags proactively spilled: 0
Total records proactively spilled: 0

Job DAG:
job_1498887855726_0004  ->      job_1498887855726_0005,
job_1498887855726_0005  ->      job_1498887855726_0006,
job_1498887855726_0006


2017-07-01 01:43:56,777 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:56,781 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:56,823 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:56,827 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:56,866 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:56,870 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:56,907 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:56,910 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:56,953 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:56,957 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:56,997 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:57,001 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:57,043 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:57,047 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:57,086 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:57,089 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:57,127 [main] INFO  org.apache.hadoop.yarn.client.RMProxy - Connecting to ResourceManager at /0.0.0.0:8032
2017-07-01 01:43:57,131 [main] INFO  org.apache.hadoop.mapred.ClientServiceDelegate - Application state is completed. FinalApplicationStatus=SUCCEEDED. Redirecting to job history server
2017-07-01 01:43:57,178 [main] WARN  org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.MapReduceLauncher - Encountered Warning UDF_WARNING_1 155 time(s).
2017-07-01 01:43:57,178 [main] INFO  org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.MapReduceLauncher - Success!

resultbylogtypetext = order frequencies by logtypetext asc;

dump resultbylogtypetext;

store resultbylogtypetext into '/output/logbytext';

Java Class  UpperUDFForPig:

package com.mjyutika;
import java.io.IOException; 
import org.apache.pig.EvalFunc; 
import org.apache.pig.data.Tuple;

public class UpperUDFForPig extends EvalFunc{ 

	   public String exec(Tuple input) throws IOException {   
	      if (input == null || ((Tuple) input).size() == 0)      
	      return null;      
	      String str = (String)input.get(0);      
	      return str.toUpperCase();  
	   } 
	}


register '/home/notroot/lab/programs/UpperUDFForpig.jar';

resultbylogtypetext = order frequencies by UpperUDFForPig(logtypetext) asc;

Output : logbycount

(UpgradeUtils/DumpUpgradeTable,442)
(UpgradeUtils/RemoveExistingFiles,399)
(UpgradeUtils/UpdateUpgradeTable,240)
(MSI ERROR,136)
(UpgradeUtils/GetPropertiesForSecureCustom,122)
(GetProperty Success,121)
(UpgradeUtils/StreamlineCmdLine,106)
(SetProperty Success,90)
(WritePrivateProfileString Success,53)
(UpgradeUtils/IsInstallFromAdminImage,36)
(UpgradeUtils/IsUninstall,21)
(SUCCESS,15)
(SetTargetPath Success,10)
(UpgradeUtils/FileExists,9)
(UpgradeUtils/StartAutoLog,7)
(GetTargetPath Success,6)
(UpgradeUtils/WriteIniFile,6)
(UpgradeUtils/SetInstallType,6)
(Function IsNotesPluginInstall,6)
(UpgradeUtils/FinalActions,5)
(UpgradeUtils/ES_SetProperties,5)
(UpgradeUtils/CountNotes6Products,4)
(UpgradeUtils/CheckKitRequiredFiles,4)
(UpgradeUtils/RemoveObsoleteFiles,3)
(UpgradeUtils/RegisterMAPI,3)
(Function INTL_IsMuiIns,3)
(UpgradeUtils/CreateSignatureFiles,3)
(UpgradeUtils/SetProgramFolderToProgramFolderVar,3)
(UpgradeUtils/uiInitialize,3)
(UpgradeUtils/SetREDIR_DATADIR,2)
(UpgradeUtils/SetPropertiesForSecureProperty,2)
(UpgradeUtils/INTL_ClearOutIniForUpgrade,2)
(UpgradeUtils/CleanLockPermissionsTable,2)
(UpgradeUtils/Check_VDIR_INI_OVERRIDE,2)
(UpgradeUtils/UpdateRegForMultiUser,2)
(UpgradeUtils/SetREDIR_VDIR_SHARED,2)
(UpgradeUtils/SetPathsFromRegistry,2)
(UpgradeUtils/IsMSIMajorUpgrade,2)
(UpgradeUtils/UI_SetProperties,2)
(INTL_GetReleaseBuild,1)
(Function IsUpgradeBuild,1)
(UpgradeUtils/DisableMUIPack,1)
(UpgradeUtils/INTL_UpdateMUIDAT,1)
(UpgradeUtils/INTL_AdjustComponentsState,1)
(PAUL->,1)
grunt>

Output : logbytext

notroot@ubuntu:~/lab/software/hadoop-2.7.2/sbin$ hdfs dfs -cat /output/logbytext/part*
Function INTL_IsMuiIns  3
Function IsNotesPluginInstall   6
Function IsUpgradeBuild 1
GetProperty Success     121
GetTargetPath Success   6
INTL_GetReleaseBuild    1
MSI ERROR       136
PAUL->  1
SUCCESS 15
SetProperty Success     90
SetTargetPath Success   10
UpgradeUtils/CheckKitRequiredFiles      4
UpgradeUtils/Check_VDIR_INI_OVERRIDE    2
UpgradeUtils/CleanLockPermissionsTable  2
UpgradeUtils/CountNotes6Products        4
UpgradeUtils/CreateSignatureFiles       3
UpgradeUtils/DisableMUIPack     1
UpgradeUtils/DumpUpgradeTable   442
UpgradeUtils/ES_SetProperties   5
UpgradeUtils/FileExists 9
UpgradeUtils/FinalActions       5
UpgradeUtils/GetPropertiesForSecureCustom       122
UpgradeUtils/INTL_AdjustComponentsState 1
UpgradeUtils/INTL_ClearOutIniForUpgrade 2
UpgradeUtils/INTL_UpdateMUIDAT  1
UpgradeUtils/IsInstallFromAdminImage    36
UpgradeUtils/IsMSIMajorUpgrade  2
UpgradeUtils/IsUninstall        21
UpgradeUtils/RegisterMAPI       3
UpgradeUtils/RemoveExistingFiles        399
UpgradeUtils/RemoveObsoleteFiles        3
UpgradeUtils/SetInstallType     6
UpgradeUtils/SetPathsFromRegistry       2
UpgradeUtils/SetProgramFolderToProgramFolderVar 3
UpgradeUtils/SetPropertiesForSecureProperty     2
UpgradeUtils/SetREDIR_DATADIR   2
UpgradeUtils/SetREDIR_VDIR_SHARED       2
UpgradeUtils/StartAutoLog       7
UpgradeUtils/StreamlineCmdLine  106
UpgradeUtils/UI_SetProperties   2
UpgradeUtils/UpdateRegForMultiUser      2
UpgradeUtils/UpdateUpgradeTable 240
UpgradeUtils/WriteIniFile       6
UpgradeUtils/uiInitialize       3
WritePrivateProfileString Success       53























