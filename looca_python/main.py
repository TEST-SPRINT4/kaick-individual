import time
import psutil
import pymssql
from datetime import datetime

# Configurações SQL Server
sql_server_cnx = pymssql.connect(
    server='52.45.220.247',
    database='test',
    user='sa',
    password='sptech'
)
cursor_sql_server = sql_server_cnx.cursor()

while True:
    
    for i in [1, 2, 3]:
        dia = datetime.now()
        cpuPercent = psutil.cpu_percent()
        ramPercent = psutil.virtual_memory().percent

        if sql_server_cnx:
            print("|               Banco conectado               |\n----------------------------------------- ")
            print("-------------------------------------------")
            print("| Porcentagem de uso de CPU: {:.2f}%".format(cpuPercent), " |\n------------------------------------------- ")
            print("---------------------------------------------")
            print("| Porcentagem de uso de RAM: {:.2f}%".format(ramPercent), " |\n--------------------------------------------- ")
            print("-------------------------------------------------------------")

            dadosInsertCpu = [cpuPercent]
            dadosInsertRam = [ramPercent]

            sql22_sql_server = "INSERT INTO RegistrosTRUSTED (dadosCapturados, dataHora, fkComponente, fkIdServidor) VALUES (%s, %s, %s, %s)"
            values22_sql_server = (dadosInsertCpu, dia.strftime('%Y-%m-%d %H:%M:%S'), 1, 3)

            sql33_sql_server = "INSERT INTO RegistrosTRUSTED (dadosCapturados, dataHora, fkComponente, fkIdServidor) VALUES (%s, %s, %s, %s)"
            values33_sql_server = (dadosInsertRam, dia.strftime('%Y-%m-%d %H:%M:%S'), 2, 3)

            try:
                cursor_sql_server.execute(sql22_sql_server, values22_sql_server)
                cursor_sql_server.execute(sql33_sql_server, values33_sql_server)
                sql_server_cnx.commit()

            except Exception as e:
                print(f"Erro ao inserir no SQL Server: {e}")

    time.sleep(5)

