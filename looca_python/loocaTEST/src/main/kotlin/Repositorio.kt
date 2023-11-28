import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar() {
        jdbcTemplate = Conexao().jdbcTemplate!!
    }

    fun insertListagem(novaDataHora: LocalDateTime, totalProcessos: Int) {
        var dataHora = novaDataHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
        jdbcTemplate.update("""
            INSERT INTO Listagem_Processos (dataHora, total_processos, fkIdServidor) VALUES
            ('$dataHora', $totalProcessos, 3);
        """)
    }

    fun insertProcesso(novoProcesso: String, novoPID: Int, novoCPU: String, novoRAM: String, novaFK: Int) {
        jdbcTemplate.update("""
            INSERT INTO Processos (nome_processo, PID, usoCPU, usoRAM, fkListagem) VALUES
            ('$novoProcesso', $novoPID, $novoCPU, $novoRAM, $novaFK);
        """)
    }

}