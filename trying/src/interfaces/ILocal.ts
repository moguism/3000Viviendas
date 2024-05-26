import type ICommunity from "./ICommunity";
import type ITipoLocal from "./ITipoLocal";

export default interface ILocal {
    id: number,
    nombre: string,
    comunidad: ICommunity,
    tipoLocal: ITipoLocal,
    nombreTipo: string
}