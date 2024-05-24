import type ILocal from "./ILocal";

export default interface ITipoLocal {
    id: number,
    nombre: string,
    locales: Array<ILocal>
}