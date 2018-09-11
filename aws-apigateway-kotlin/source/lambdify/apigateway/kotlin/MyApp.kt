package lambdify.apigateway.kotlin

import lambdify.apigateway.*

/**
 * Created by miere.teixeira on 08/04/2018.
 */
class MyApp: APIGatewayApp({

    val userResource = UserResource()

    routes(
        Methods.POST and "/users/:id" withNoResponse userResource::saveUser,
        Methods.GET  and "/users"     withNoArgs     userResource::retrieveUsers,
        Methods.GET  and "/"          withNoArgs     userResource::root
    )
})

class UserResource {

    val data = mutableMapOf<String,String>()

    fun saveUser( request: APIGatewayRequest){
        data[request.pathParameters!!["id"]!!] = request.body!!
    }

    fun retrieveUsers() = APIGatewayResponse.ok( data.values.joinToString { it } )

    fun root() = APIGatewayResponse.ok( "Hello World" )
}
