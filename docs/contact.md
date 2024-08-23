# Contact API Spec

## Create Contact

Enpoint : POST /api/contacts

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "random-string",
    "firstName" : "Anton Roycar",
    "lastName"  : "Nababan",
    "email" : "anton@example.com",
    "phone" : "089988776611"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

## Update Contact

Enpoint : PUT /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Success) :
```json
{
  "id" : "random-string",
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

## Get Contact

Enpoint : GET /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "id" : "random-string",
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Failed, 404) :
```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact

Enpoint : GET /api/contacts

Query Param :
- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : [
    {
    "id" : "random-string",
    "firstName" : "Anton Roycar",
    "lastName"  : "Nababan",
    "email" : "anton@example.com",
    "phone" : "089988776611"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```
## Remove Contact

Enpoint : DELETE /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Contact is not found"
}
```
