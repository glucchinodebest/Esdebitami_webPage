# ContextsApi

All URIs are relative to *https://localhost:8045/vvas-ir*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteContextUsingDELETE**](ContextsApi.md#deleteContextUsingDELETE) | **DELETE** /contexts/{id} | Delete a context
[**getContextUsingGET**](ContextsApi.md#getContextUsingGET) | **GET** /contexts/{id} | Get a context by id
[**getContextsUsingGET**](ContextsApi.md#getContextsUsingGET) | **GET** /contexts | Get the list of available contexts
[**postContextUsingPOST**](ContextsApi.md#postContextUsingPOST) | **POST** /contexts | Create a new context
[**putContextUsingPUT**](ContextsApi.md#putContextUsingPUT) | **PUT** /contexts/{id} | Update a context


<a name="deleteContextUsingDELETE"></a>
# **deleteContextUsingDELETE**
> deleteContextUsingDELETE(id)

Delete a context

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContextsApi;


ContextsApi apiInstance = new ContextsApi();
String id = "id_example"; // String | Context id
try {
    apiInstance.deleteContextUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ContextsApi#deleteContextUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Context id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getContextUsingGET"></a>
# **getContextUsingGET**
> Context getContextUsingGET(id)

Get a context by id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContextsApi;


ContextsApi apiInstance = new ContextsApi();
String id = "id_example"; // String | Context id
try {
    Context result = apiInstance.getContextUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ContextsApi#getContextUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Context id |

### Return type

[**Context**](Context.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getContextsUsingGET"></a>
# **getContextsUsingGET**
> List&lt;Context&gt; getContextsUsingGET(page, size, templateId)

Get the list of available contexts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContextsApi;


ContextsApi apiInstance = new ContextsApi();
Integer page = 56; // Integer | Zero-based page index
Integer size = 56; // Integer | The size of the page to be returned
String templateId = "templateId_example"; // String | Template id
try {
    List<Context> result = apiInstance.getContextsUsingGET(page, size, templateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ContextsApi#getContextsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**| Zero-based page index | [optional]
 **size** | **Integer**| The size of the page to be returned | [optional]
 **templateId** | **String**| Template id | [optional]

### Return type

[**List&lt;Context&gt;**](Context.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="postContextUsingPOST"></a>
# **postContextUsingPOST**
> URI postContextUsingPOST(context)

Create a new context

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContextsApi;


ContextsApi apiInstance = new ContextsApi();
Context context = new Context(); // Context | Context object
try {
    URI result = apiInstance.postContextUsingPOST(context);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ContextsApi#postContextUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **context** | [**Context**](Context.md)| Context object |

### Return type

[**URI**](URI.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="putContextUsingPUT"></a>
# **putContextUsingPUT**
> putContextUsingPUT(context, id)

Update a context

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContextsApi;


ContextsApi apiInstance = new ContextsApi();
Context context = new Context(); // Context | Context object
String id = "id_example"; // String | Context id
try {
    apiInstance.putContextUsingPUT(context, id);
} catch (ApiException e) {
    System.err.println("Exception when calling ContextsApi#putContextUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **context** | [**Context**](Context.md)| Context object |
 **id** | **String**| Context id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

