import type ICommunity from "@/interfaces/ICommunity.ts"

export default class CommunityService {

    baseUri:string = "http://127.0.0.1:8080/api"

    async listAllCommunities():Promise<Array<ICommunity>> {
        const uri = `${this.baseUri}/comunidades`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createCommunity(name:string, address:string) {
        const data:ICommunity = {
            id: null,
            nombre: name,
            direccion: address,
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}