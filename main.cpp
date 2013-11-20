#include <QApplication>
#include "tcpserver.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    
    TCPServer server;
    return a.exec();
}
