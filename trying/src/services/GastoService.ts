import type ICommunity from "@/interfaces/ICommunity";
import type IGasto from "@/interfaces/IGasto";
import type ITipoGasto from "@/interfaces/ITipoGasto";

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

    async createGasto(monto: number, fecha: Date, tipoGasto: ITipoGasto, comunidad: ICommunity) {
        const data:IGasto = {
            id: 0,
            monto: monto,
            fecha: fecha,
            tipoGasto: tipoGasto,
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

    async deleteGasto(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateGasto(id: number, monto: number, fecha: Date, tipoGasto: ITipoGasto, comunidad: ICommunity){
        const uri = `${this.baseUri}/${id}`
        const data:IGasto = {
            id: 0,
            monto: monto,
            fecha: fecha,
            tipoGasto: tipoGasto,
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
