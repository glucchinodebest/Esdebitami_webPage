# ResourcesApi

All URIs are relative to *https://localhost:8045/vvas-ir*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteResourcesUsingDELETE**](ResourcesApi.md#deleteResourcesUsingDELETE) | **DELETE** /contexts/{id}/resources | Delete all resources associated with a context
[**postResourceUsingPOST**](ResourcesApi.md#postResourceUsingPOST) | **POST** /contexts/{id}/resources | Create a new resource


<a name="deleteResourcesUsingDELETE"></a>
# **deleteResourcesUsingDELETE**
> deleteResourcesUsingDELETE(id)

Delete all resources associated with a context

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ResourcesApi;


ResourcesApi apiInstance = new ResourcesApi();
String id = "id_example"; // String | Context id
try {
    apiInstance.deleteResourcesUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ResourcesApi#deleteResourcesUsingDELETE");
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

<a name="postResourceUsingPOST"></a>
# **postResourceUsingPOST**
> URI postResourceUsingPOST(id, resource)

Create a new resource

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ResourcesApi;


ResourcesApi apiInstance = new ResourcesApi();
String id = "id_example"; // String | Context id
Resource resource = new Resource(); // Resource | Resource object
try {
    URI result = apiInstance.postResourceUsingPOST(id, resource);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResourcesApi#postResourceUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Context id |
 **resource** | [**Resource**](Resource.md)| Resource object |

### Return type

[**URI**](URI.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

