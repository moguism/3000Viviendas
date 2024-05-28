import type IIngreso from "@/interfaces/IIngreso";
import type ITipoIngreso from "@/interfaces/ITipoIngreso";

export default class TipoDeudaService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-ingreso"

    async listAllTiposIngreso(): Promise<Array<ITipoIngreso>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoIngresoById(id: number): Promise<ITipoIngreso> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoIngreso(name: string, ingresos: Array<IIngreso>) {
        const data:ITipoIngreso = {
            id: 0,
            nombre: name,
            ingresos: ingresos
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

    async deleteTipoIngreso(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateTipoIngreso(id: number, name: string, ingresos: Array<IIngreso>) {
        const data:ITipoIngreso = {
            id: id,
            nombre: name,
            ingresos: ingresos
        }
        const uri = `${this.baseUri}/${id}`
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