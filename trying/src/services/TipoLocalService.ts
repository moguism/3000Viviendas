import type ITipoLocal from "@/interfaces/ITipoLocal";
import type ILocal from "@/interfaces/ILocal";

export default class TipoLocalService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-local"

    async listAllTiposLocales(): Promise<Array<ITipoLocal>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoLocalById(id: number): Promise<ITipoLocal> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoLocal(name: string, locales: Array<ILocal>) {
        const data: ITipoLocal = {
            id: 0,
            nombre: name,
            locales: locales
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

    async deleteTipoLocal(id: number) {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateTipoLocal(id: number, name: string, locales: Array<ILocal>) {
        {
            const data: ITipoLocal = {
                id: id,
                nombre: name,
                locales: locales
            }
            const uri = `${this.baseUri}/${id}`
            const rawResponse = await fetch(uri, {
                method: 'PUT',
                body: JSON.stringify(data)
            })
            const response = await rawResponse.json()
            return response
        }

    }
}