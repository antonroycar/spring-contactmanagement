# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "anton",
  "password" : "rahasia",
  "name" : "Anton Roycar Nababan"
}
```

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username can't black, ???"
}
```

## Login User
Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "anton",
  "password" : "rahasia"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 1234567890 //millisecond
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username or password wrong, ???"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "username" : "anton",
    "name" : "Anton Roycar Nababan"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorized"
}
```

## Update User


Endpoint : PATCH /api/users/current

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "name" : "Anton Roycar Nababan", //put if only want to update name
  "password" : "new password" //put if only want to update password
}
```


Response Body (Success) :
```json
{
  "data" : {
    "username" : "anton",
    "name" : "Anton Roycar Nababan"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorized"
}
```

## Logout User


Endpoint : DELETE /api/users/logout

Request Header :
- X -API-TOKEN : Token (Mandatory)
  Response Body (Success) :

```json
{
  "data" : "OK"
}
```