#include "tcpserver.h"

TCPServer::TCPServer(QObject *parent):
    QObject(parent)
{
    server = new QTcpServer(this);

    connect(server,SIGNAL(newConnection()),this,SLOT(nConnection()));

    if(!server->listen(QHostAddress::Any, 1500))
    {
        qDebug() << "O servidor nao pode ser iniciado!";
    }
    else
    {
        qDebug() << "O servidor iniciado na porta 1500.";
    }
}

void TCPServer::nConnection()
{
    QTcpSocket *socket = server->nextPendingConnection();

    socket->write("hello client\r\n");
    socket->flush();

    socket->waitForBytesWritten(3000);

    socket->close();
}


