import type ICommunity from "@/interfaces/ICommunity.ts"

export default class CommunityService {

    baseUri:string = "http://127.0.0.1:8080/api/comunidades"

    async listAllCommunities():Promise<Array<ICommunity>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listCommunityById(id:number):Promise<ICommunity> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createCommunity(name:string, address:string) {
        const data:ICommunity = {
            id: 0,
            nombre: name,
            direccion: address,
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteComunidad(id: number){

        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateComunidad(id:number, name:string, address:string) {
        const uri = `${this.baseUri}/${id}`
        const data:ICommunity = {
            id : 0,
            nombre: name,
            direccion: address,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}