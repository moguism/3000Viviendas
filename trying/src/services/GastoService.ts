import type IGasto from "@/interfaces/IGasto"

export default class GastoService {

    baseUri: string = "http://127.0.0.1:8080/api/gastos"

    async listAllGastos(): Promise<Array<IGasto>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listGastoById(id: number): Promise<IGasto> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createGasto(nombre: string){  
        const data:IGasto = {
            id: 0,
            nombre: nombre,
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

    async deleteGasto(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateGasto(id: number, nombre: string){
        const uri = `${this.baseUri}/${id}`
        const data:IGasto = {
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