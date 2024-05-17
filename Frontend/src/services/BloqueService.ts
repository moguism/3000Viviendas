import type IBloque from "@interfaces/IBloque.ts"


export default class BloqueService {

    baseUri:string = "http://127.0.0.1:8080/api"

    async listAllBloques():Promise<Array<IBloque>> {
        const uri = `${this.baseUri}/bloques`
        const rawResponse = await fetch(uri)
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
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}