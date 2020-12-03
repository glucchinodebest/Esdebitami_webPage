
# Context

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cron** | **String** | The cron expression that describe individual details of the schedule. |  [optional]
**css** | **String** | The css query to find resources within the context |  [optional]
**cssRef** | **String** | The css query to find the reference (e.g.: title) within the context resource |  [optional]
**date** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**id** | **String** | The database generated context ID |  [optional]
**language** | [**LanguageEnum**](#LanguageEnum) | The ISO 639-1 code of the language used in context resources |  [optional]
**template** | **String** | The template to which the context refers |  [optional]
**uri** | **String** | The sources of the context |  [optional]


<a name="LanguageEnum"></a>
## Enum: LanguageEnum
Name | Value
---- | -----
EN | &quot;EN&quot;
IT | &quot;IT&quot;
DA | &quot;DA&quot;
DE | &quot;DE&quot;
NL | &quot;NL&quot;
PT | &quot;PT&quot;
SE | &quot;SE&quot;



