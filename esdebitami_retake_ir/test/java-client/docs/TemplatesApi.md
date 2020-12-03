# TemplatesApi

All URIs are relative to *https://localhost:8045/vvas-ir*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAnswersUsingGET**](TemplatesApi.md#getAnswersUsingGET) | **GET** /templates/{id}/answers | Retrieve possible answers to a question posed within a context
[**postIndexUsingPOST**](TemplatesApi.md#postIndexUsingPOST) | **POST** /contexts/{id}/indexes | Create a lexical index from the contexts of the specified template


<a name="getAnswersUsingGET"></a>
# **getAnswersUsingGET**
> List&lt;Answer&gt; getAnswersUsingGET(id, count, language, question)

Retrieve possible answers to a question posed within a context

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TemplatesApi;


TemplatesApi apiInstance = new TemplatesApi();
String id = "id_example"; // String | Template id
Integer count = 1; // Integer | The number of answers to be retrieved
String language = "language_example"; // String | Language
String question = "question_example"; // String | Question
try {
    List<Answer> result = apiInstance.getAnswersUsingGET(id, count, language, question);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplatesApi#getAnswersUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Template id |
 **count** | **Integer**| The number of answers to be retrieved | [optional] [default to 1]
 **language** | **String**| Language | [optional] [enum: EN, IT, DA, DE, NL, PT, SE]
 **question** | **String**| Question | [optional]

### Return type

[**List&lt;Answer&gt;**](Answer.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="postIndexUsingPOST"></a>
# **postIndexUsingPOST**
> postIndexUsingPOST(id)

Create a lexical index from the contexts of the specified template

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TemplatesApi;


TemplatesApi apiInstance = new TemplatesApi();
String id = "id_example"; // String | Template id
try {
    apiInstance.postIndexUsingPOST(id);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplatesApi#postIndexUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Template id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

