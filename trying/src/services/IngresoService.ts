import type IIngreso from "@/interfaces/IIngreso";
import type ITipoIngreso from "@/interfaces/ITipoIngreso";
import type ICommunity from "@/interfaces/ICommunity";

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

    async createIngreso(monto: number, fecha: Date, tipoIngreso: ITipoIngreso, comunidad: ICommunity) {
        const data:IIngreso = {
            id: 0,
            monto: monto,
            fecha: fecha,
            tipoIngreso: tipoIngreso,
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

    async deleteIngreso(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateIngreso(id: number, monto: number, fecha: Date, tipoIngreso: ITipoIngreso, comunidad: ICommunity){
        const uri = `${this.baseUri}/${id}`
        const data:IIngreso = {
            id: 0,
            monto: monto,
            fecha: fecha,
            tipoIngreso: tipoIngreso,
            comunidad: comunidad
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}