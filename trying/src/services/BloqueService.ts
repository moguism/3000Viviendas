import type IBloque from "@/interfaces/IBloque.ts"


export default class BloqueService {

    baseUri:string = "http://127.0.0.1:8080/api/bloques"

    async listAllBloques():Promise<Array<IBloque>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async createBloque(comunidad_id:number) {
        const data:IBloque = {
            id: 0,
            comunidad_id: comunidad_id,
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
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateBloque(id:number, comunidad_id:number) {
        const uri = `${this.baseUri}/${id}`
        const data:IBloque = {
            id : 0,
            comunidad_id: comunidad_id,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}