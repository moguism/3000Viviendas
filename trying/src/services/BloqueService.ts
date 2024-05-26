import type IBloque from "@/interfaces/IBloque.ts"
import type ICommunity from "@/interfaces/ICommunity"


export default class BloqueService {

    baseUri:string = "http://127.0.0.1:8080/api/bloques"

    async listAllBloques():Promise<Array<IBloque>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listBloqueById(id: number): Promise<IBloque> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createBloque(comunidad: ICommunity) {
        const data:IBloque = {
            id: 0,
            comunidad: comunidad
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

    async deleteBloque(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateBloque(id:number, comunidad: ICommunity) {
        const uri = `${this.baseUri}/${id}`
        const data:IBloque = {
            id : 0,
            comunidad: comunidad
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}