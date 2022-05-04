
data "openstack_images_image_v2" "ubuntu" {
  name        = "Ubuntu 20.04"
  most_recent = true

  properties = {
    key = "value"
  }
}

# output "out" {
# value = data.openstack_images_image_v2.ubuntu
# }


resource "openstack_compute_instance_v2" "mattermost-bots" {
  name        = "mattermost-bots"
  image_id    = data.openstack_images_image_v2.ubuntu.id
  flavor_name = "m1.small"
  key_pair    = "talpert"
  metadata    = { this = "if this work, that would be wonderful" }
  security_groups = ["mattermost-bots", "default" ]

  provisioner "remote-exec" {
    # define SSH connection for provisioner
    connection {
      type = "ssh"
      host = self.access_ip_v4
      user = "ubuntu"
      # private_key = file("~/.ssh/id_rsa")
    }
    inline = [
      "sudo apt update",
      "sudo apt install docker.io -y ",
      "sudo apt install docker-compose -y ",
      "sudo usermod -aG docker $USER",
      "sudo docker network create botnetwork",
      "sudo apt install unzip -y"

    ]
  }
}


output "NewIP" {
value = openstack_compute_instance_v2.mattermost-bots.access_ip_v4
}









resource "null_resource" "softwareconfig" {
  connection {
    type = "ssh"
    host = openstack_compute_instance_v2.mattermost-bots.access_ip_v4
    user = "ubuntu"
    port = 22
  }

   provisioner "file" {
   source      = "./docker"
   destination = "/home/ubuntu/bots"
   }

   provisioner "remote-exec" {
   inline = [
      "cd bots/mattermost-bots/build/libs/",
      "unzip *",
      "cd ~/bots/mattermost-bots",
      "docker build -t local/bot .",
      "cd ~/bots/cleverserver",
      "docker build -t local/cleverserver .",
      "cd ~/bots",
      "docker-compose up -d"
    ]
   }

}











