import type ICommunity from "./ICommunity";
import type IVivienda from "./IVivienda";

export default interface IBloque {
    id: number,
    comunidad: ICommunity,
    viviendas: Array<IVivienda>
}