resource "aws_instance" "financeme-app-new" {
  ami           = "ami-00ca32bbc84273381"  
  instance_type = "t3.small"
  key_name      = "mikey"                   

  tags = {
    Name = "FinanceAppMenew"
  }

  # Provisioner section runs commands on the instance after creation
  provisioner "remote-exec" {
    inline = [
      "sudo yum update -y",
      "sudo yum install docker -y",
      "sudo service docker start",
      "sudo usermod -aG docker ec2-user",
      "sudo docker run -d -p 8080:8080 kiranreddykr7/financeme-account-service:latest"
    ]

    connection {
      type        = "ssh"
      user        = "ec2-user"
      private_key = file("C:/Users/kiran/Downloads/mikey.pem")  # Full Windows path
      host        = self.public_ip
    }
  }
}
