# ContentsApi

All URIs are relative to *https://localhost:8045/vvas-ir*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteContentsUsingDELETE**](ContentsApi.md#deleteContentsUsingDELETE) | **DELETE** /resources/{id}/contents | Delete all contents associated with a resource
[**getContentUsingGET**](ContentsApi.md#getContentUsingGET) | **GET** /resources/*/contents/{id} | Get a content by id
[**postContentUsingPOST**](ContentsApi.md#postContentUsingPOST) | **POST** /resources/{id}/contents | Create a new content


<a name="deleteContentsUsingDELETE"></a>
# **deleteContentsUsingDELETE**
> deleteContentsUsingDELETE(id)

Delete all contents associated with a resource

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContentsApi;


ContentsApi apiInstance = new ContentsApi();
String id = "id_example"; // String | Resource id
try {
    apiInstance.deleteContentsUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ContentsApi#deleteContentsUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Resource id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getContentUsingGET"></a>
# **getContentUsingGET**
> Content getContentUsingGET(id)

Get a content by id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContentsApi;


ContentsApi apiInstance = new ContentsApi();
String id = "id_example"; // String | Content id
try {
    Content result = apiInstance.getContentUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ContentsApi#getContentUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Content id |

### Return type

[**Content**](Content.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="postContentUsingPOST"></a>
# **postContentUsingPOST**
> URI postContentUsingPOST(content, id)

Create a new content

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ContentsApi;


ContentsApi apiInstance = new ContentsApi();
Content content = new Content(); // Content | Content object
String id = "id_example"; // String | Resource id
try {
    URI result = apiInstance.postContentUsingPOST(content, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ContentsApi#postContentUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **content** | [**Content**](Content.md)| Content object |
 **id** | **String**| Resource id |

### Return type

[**URI**](URI.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

