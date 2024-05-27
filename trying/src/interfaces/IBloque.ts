import type ICommunity from "./ICommunity";
import type IMensualidad from "./IMensualidad";
import type IReunion from "./IReunion";
import type IVivienda from "./IVivienda";

export default interface IBloque {
    id: number,
    comunidad: ICommunity,
    viviendas: Array<IVivienda>,
    reuniones: Array<IReunion>,
    mensualidades: Array<IMensualidad>
}