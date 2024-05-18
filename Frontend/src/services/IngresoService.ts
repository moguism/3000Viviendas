import type IIngreso from "@/interfaces/IIngreso";

export default class IngresoService {

    baseUri: string = "http://127.0.0.1:8080/api/ingresos"

    async listAllIngresos(): Promise<Array<IIngreso>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listIngresoById(id: number): Promise<IIngreso> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createIngreso(nombre: string){
        const data:IIngreso = {
            id: 0,
            nombre: nombre,
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteIngreso(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateIngreso(id: number, nombre: string){
        const uri = `${this.baseUri}/${id}`
        const data:IIngreso = {
            id: 0,
            nombre: nombre,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}