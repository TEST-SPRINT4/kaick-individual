import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {
    val repositorio = Repositorio()
    repositorio.iniciar()

    val looca = Looca()
    val grupoDeProcessos: ProcessoGrupo = looca.getGrupoDeProcessos()
    val processos: List<Processo> = grupoDeProcessos.processos

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ")
    val dataHora: LocalDateTime = LocalDateTime.now()
    println()

    var cont = 0

    Timer().scheduleAtFixedRate(object : TimerTask() {

        override fun run() {
            cont++
            repositorio.insertListagem(dataHora, grupoDeProcessos.totalProcessos)
            println("LISTAGEM DE PROCESSOS: $cont")
            println("Data e Hora: " + dataHora.format(formatter))
            for (processo in processos) {
                val usoCpu = String.format(Locale.US, "%.6f", processo.usoCpu)
                val usoRam = String.format(Locale.US, "%.6f", processo.usoMemoria)
                println(
                    """
                        Processo: ${processo.nome}; PID: ${processo.pid}
                        Percentual de uso da CPU: $usoCpu% 
                        Percentual de uso da RAM: $usoRam% 
                    """.trimIndent()
                )
                    repositorio.insertProcesso(
                    novoProcesso = processo.nome, novoPID = processo.pid, novoCPU = usoCpu, novoRAM = usoRam, novaFK = cont
                )
            }
            println("Total de Processos em execução: ${grupoDeProcessos.totalProcessos}")
        }

    },0, 1000)

}

